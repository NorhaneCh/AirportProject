import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getHangar } from "../../api/hangars";

export default function HangarDetails() {
  const { id } = useParams();
  const [hangar, setHangar] = useState(null);

  useEffect(() => {
    getHangar(id).then(setHangar);
  }, [id]);

  if (!hangar) return <div className="p-4">Chargement...</div>;

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Détails du hangar</h1>
      <div className="border p-4 rounded bg-white shadow space-y-2">
        <p><strong>ID :</strong> {hangar.id}</p>
        <p><strong>Capacité :</strong> {hangar.capacite}</p>
        <p><strong>État :</strong> {hangar.etat}</p>
        <p><strong>Avions :</strong> {hangar.avions.length ? hangar.avions.map(a => a.immatriculation).join(", ") : "Aucun"}</p>
      </div>
      <Link to={`/hangars/${hangar.id}/edit`} className="mt-4 inline-block px-4 py-2 bg-blue-600 text-white rounded">
        Modifier
      </Link>
    </div>
  );
}
