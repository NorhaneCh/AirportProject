import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getAircraft } from "../api/aircraft";

export default function AircraftDetails() {
  const { id } = useParams();
  const [aircraft, setAircraft] = useState(null);

  useEffect(() => {
    getAircraft(id).then((a) => {
      if (!a) return;
      setAircraft({
        registration: a.immatriculation,
        type: a.type,
        capacity: a.capacite,
        status: a.etat,
      });
    });
  }, [id]);

  if (!aircraft) return <div className="p-4">Chargement...</div>;

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Détails de l’avion</h1>

      <div className="space-y-2 border p-4 rounded bg-white shadow">
        <p><strong>Immatriculation :</strong> {aircraft.registration}</p>
        <p><strong>Type :</strong> {aircraft.type}</p>
        <p><strong>Capacité :</strong> {aircraft.capacity}</p>
        <p><strong>État :</strong> {aircraft.status}</p>
      </div>

      <Link
        to={`/aircraft/${aircraft.registration}/edit`}
        className="mt-4 inline-block px-4 py-2 bg-blue-600 text-white rounded"
      >
        Modifier
      </Link>
    </div>
  );
}
