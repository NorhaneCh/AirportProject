import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getPiste } from "../../api/pistes";

export default function RunwayDetails() {
  const { id } = useParams();
  const [runway, setRunway] = useState(null);

  useEffect(() => {
    getPiste(id).then(setRunway);
  }, [id]);

  if (!runway) return <div className="p-4">Chargement...</div>;

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Détails de la piste</h1>
      <div className="border p-4 rounded bg-white shadow space-y-2">
        <p><strong>ID :</strong> {runway.id}</p>
        <p><strong>Nom :</strong> {runway.nom || `Piste ${runway.id}`}</p>
        <p><strong>Longueur :</strong> {runway.longueur} m</p>
        <p><strong>État :</strong> {runway.etat}</p>
      </div>
      <Link to={`/runways/${runway.id}/edit`} className="mt-4 inline-block px-4 py-2 bg-blue-600 text-white rounded">
        Modifier
      </Link>
    </div>
  );
}
