import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getAllPistes, deletePiste } from "../../api/pistes";

export default function RunwayList() {
  const [pistes, setPistes] = useState([]);

  useEffect(() => {
    getAllPistes().then(setPistes);
  }, []);

  const handleDelete = async (id) => {
    await deletePiste(id);
    setPistes(prev => prev.filter(p => p.id !== id));
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Liste des pistes</h1>
        <Link to="/runways/new" className="px-4 py-2 bg-blue-600 text-white rounded">Ajouter une piste</Link>
      </div>

      <table className="w-full border">
        <thead className="bg-gray-100">
          <tr>
            <th className="p-2 border">Nom</th>
            <th className="p-2 border">Longueur (m)</th>
            <th className="p-2 border">État</th>
            <th className="p-2 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          {pistes.map(p => (
            <tr key={p.id} className="border">
              <td className="p-2 border">{p.nom || `Piste ${p.id}`}</td>
              <td className="p-2 border">{p.longueur}</td>
              <td className="p-2 border">{p.etat}</td>
              <td className="p-2 border space-x-3">
                <Link to={`/runways/${p.id}`} className="text-blue-600">Détails</Link>
                <Link to={`/runways/${p.id}/edit`} className="text-green-600">Modifier</Link>
                <button onClick={() => handleDelete(p.id)} className="text-red-600">Supprimer</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
