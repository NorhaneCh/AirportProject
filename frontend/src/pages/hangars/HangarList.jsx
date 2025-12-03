import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getAllHangars, deleteHangar } from "../../api/hangars";

export default function HangarList() {
  const [hangars, setHangars] = useState([]);

  useEffect(() => {
    getAllHangars().then(setHangars);
  }, []);

  const handleDelete = async (id) => {
    await deleteHangar(id);
    setHangars(prev => prev.filter(h => h.id !== id));
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Liste des hangars</h1>
        <Link to="/hangars/new" className="px-4 py-2 bg-blue-600 text-white rounded">
          Ajouter un hangar
        </Link>
      </div>

      <table className="w-full border">
        <thead className="bg-gray-100">
          <tr>
            <th className="p-2 border">ID</th>
            <th className="p-2 border">Capacité</th>
            <th className="p-2 border">État</th>
            <th className="p-2 border">Avions</th>
            <th className="p-2 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          {hangars.map(h => (
            <tr key={h.id} className="border">
              <td className="p-2 border">{h.id}</td>
              <td className="p-2 border">{h.capacite}</td>
              <td className="p-2 border">{h.etat}</td>
              <td className="p-2 border">{h.avions.length > 0 ? h.avions.map(a => a.immatriculation).join(", ") : "Aucun"}</td>
              <td className="p-2 border space-x-3">
                <Link to={`/hangars/${h.id}`} className="text-blue-600">Détails</Link>
                <Link to={`/hangars/${h.id}/edit`} className="text-green-600">Modifier</Link>
                <button onClick={() => handleDelete(h.id)} className="text-red-600">Supprimer</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
