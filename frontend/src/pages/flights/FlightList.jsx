import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

export default function FlightList() {
  const [flights, setFlights] = useState([]);

  useEffect(() => {
    setFlights([
      { id: 1, number: "AF123", origin: "Paris", destination: "New York", schedule: "10:00", status: "Prévu", aircraft: "F-ABCD" },
      { id: 2, number: "BA456", origin: "Londres", destination: "Tokyo", schedule: "15:30", status: "En vol", aircraft: "F-EFGH" },
      { id: 3, number: "LH789", origin: "Berlin", destination: "Dubai", schedule: "12:15", status: "Annulé", aircraft: null }
    ]);
  }, []);

  const deleteFlight = (id) => {
    setFlights(prev => prev.filter(f => f.id !== id));
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Liste des vols</h1>
        <Link to="/flights/new" className="px-4 py-2 bg-blue-600 text-white rounded">Ajouter un vol</Link>
      </div>

      <table className="w-full border">
        <thead className="bg-gray-100">
          <tr>
            <th className="p-2 border">Numéro</th>
            <th className="p-2 border">Origine</th>
            <th className="p-2 border">Destination</th>
            <th className="p-2 border">Horaire</th>
            <th className="p-2 border">État</th>
            <th className="p-2 border">Avion assigné</th>
            <th className="p-2 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          {flights.map(f => (
            <tr key={f.id} className="border">
              <td className="p-2 border">{f.number}</td>
              <td className="p-2 border">{f.origin}</td>
              <td className="p-2 border">{f.destination}</td>
              <td className="p-2 border">{f.schedule}</td>
              <td className="p-2 border">{f.status}</td>
              <td className="p-2 border">{f.aircraft || "Aucun"}</td>
              <td className="p-2 border space-x-3">
                <Link to={`/flights/${f.id}`} className="text-blue-600">Détails</Link>
                <Link to={`/flights/${f.id}/edit`} className="text-green-600">Modifier</Link>
                <button onClick={() => deleteFlight(f.id)} className="text-red-600">Supprimer</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
