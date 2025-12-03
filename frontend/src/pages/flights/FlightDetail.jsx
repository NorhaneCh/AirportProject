import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";

export default function FlightDetails() {
  const { id } = useParams();
  const [flight, setFlight] = useState(null);

  useEffect(() => {
    setFlight({
      id,
      number: "AF123",
      origin: "Paris",
      destination: "New York",
      schedule: "10:00",
      status: "Prévu",
      aircraft: "F-ABCD"
    });
  }, [id]);

  if (!flight) return <div className="p-4">Chargement...</div>;

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Détails du vol</h1>
      <div className="border p-4 rounded bg-white shadow space-y-2">
        <p><strong>ID :</strong> {flight.id}</p>
        <p><strong>Numéro :</strong> {flight.number}</p>
        <p><strong>Origine :</strong> {flight.origin}</p>
        <p><strong>Destination :</strong> {flight.destination}</p>
        <p><strong>Horaire :</strong> {flight.schedule}</p>
        <p><strong>État :</strong> {flight.status}</p>
        <p><strong>Avion assigné :</strong> {flight.aircraft || "Aucun"}</p>
      </div>
      <Link to={`/flights/${flight.id}/edit`} className="mt-4 inline-block px-4 py-2 bg-blue-600 text-white rounded">
        Modifier
      </Link>
    </div>
  );
}
