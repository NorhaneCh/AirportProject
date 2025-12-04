import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getAllFlights, deleteFlight } from "../../api/flights";

export default function FlightList() {
  const [flights, setFlights] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const ourAirport = "lino";

  useEffect(() => {
    const fetchFlights = async () => {
      try {
        const data = await getAllFlights();
        setFlights(data);
        console.log("flights : ", data);
      } catch (err) {
        setError("Impossible de récupérer les vols.");
        console.error(err);
      } finally {
        setLoading(false);
      }
    };

    fetchFlights();
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm("Voulez-vous vraiment supprimer ce vol ?")) return;
    try {
      await deleteFlight(id);
      setFlights((prev) => prev.filter((f) => f.id !== id));
    } catch (err) {
      setError("Impossible de supprimer le vol.");
      console.error(err);
    }
  };

  const departures = flights.filter((f) => f.origine === ourAirport);
  const arrivals = flights.filter((f) => f.destination === ourAirport);

  const renderTable = (flights) => (
    <table className="w-full border mb-6">
      <thead className="bg-gray-100">
        <tr>
          <th className="p-2 border">Numéro</th>
          <th className="p-2 border">Origine</th>
          <th className="p-2 border">Destination</th>
          <th className="p-2 border">Départ</th>
          <th className="p-2 border">Arrivée</th>
          <th className="p-2 border">État</th>
          <th className="p-2 border">Avion</th>
          <th className="p-2 border">Piste</th>
          <th className="p-2 border">Actions</th>
        </tr>
      </thead>
      <tbody>
        {flights.map((f) => (
          <tr key={f.id} className="border">
            <td className="p-2 border">{f.numero}</td>
            <td className="p-2 border">{f.origine}</td>
            <td className="p-2 border">{f.destination}</td>
            <td className="p-2 border">
              {new Date(f.dateDepart).toLocaleString()}
            </td>
            <td className="p-2 border">
              {new Date(f.dateArrivee).toLocaleString()}
            </td>
            <td className="p-2 border">{f.statut}</td>
            <td className="p-2 border">{f.avionImmatriculation || "Aucun"}</td>
            <td className="p-2 border">{f.pisteId || "Aucune"}</td>
            <td className="p-2 border space-x-3">
              <Link
                to={`/flights/${f.id}/edit`}
                className="text-green-600 hover:cursor-pointer hover:underline"
              >
                Modifier
              </Link>
              <button
                onClick={() => handleDelete(f.id)}
                className="text-red-600 hover:cursor-pointer hover:underline"
              >
                Supprimer
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );

  if (loading) return <p>Chargement des vols...</p>;
  if (error) return <p className="text-red-600">{error}</p>;

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Liste des vols</h1>
        <Link
          to="/flights/new"
          className="px-4 py-2 bg-blue-600 text-white rounded"
        >
          Ajouter un vol
        </Link>
      </div>

      <h2 className="text-xl font-semibold mb-2">
        Vols au départ de notre aéroport
      </h2>
      {departures.length > 0 ? (
        renderTable(departures)
      ) : (
        <p>Aucun vol au départ.</p>
      )}

      <h2 className="text-xl font-semibold mb-2 mt-12">
        Vols à l'arrivée à notre aéroport
      </h2>
      {arrivals.length > 0 ? (
        renderTable(arrivals)
      ) : (
        <p>Aucun vol à l'arrivée.</p>
      )}
    </div>
  );
}
