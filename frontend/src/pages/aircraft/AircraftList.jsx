import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getAllAircraft, deleteAircraft } from "../../api/aircraft";

export default function AircraftList() {
  const [aircrafts, setAircrafts] = useState([]);

  useEffect(() => {
    getAllAircraft().then((data) => {
      setAircrafts(
        data.map(a => ({
          registration: a.immatriculation,
          type: a.type,
          capacity: a.capacite,
          status: a.etat,
        }))
      );
    });
  }, []);

  const handleDelete = async (registration) => {
    await deleteAircraft(registration);
    setAircrafts(prev => prev.filter(ac => ac.registration !== registration));
  };

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-2xl font-bold">Liste des avions</h1>
        <Link to="/aircraft/new" className="px-4 py-2 bg-blue-600 text-white rounded">
          Ajouter un avion
        </Link>
      </div>

      <table className="w-full border">
        <thead className="bg-gray-100">
          <tr>
            <th className="p-2 border">Immatriculation</th>
            <th className="p-2 border">Type</th>
            <th className="p-2 border">Capacité</th>
            <th className="p-2 border">État</th>
            <th className="p-2 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          {aircrafts.map(ac => (
            <tr key={ac.registration} className="border">
              <td className="p-2 border">{ac.registration}</td>
              <td className="p-2 border">{ac.type}</td>
              <td className="p-2 border">{ac.capacity}</td>
              <td className="p-2 border">{ac.status}</td>
              <td className="p-2 border space-x-3">
                <Link to={`/aircraft/${ac.registration}`} className="text-blue-600">Détails</Link>
                <Link to={`/aircraft/${ac.registration}/edit`} className="text-green-600">Modifier</Link>
                <button
                  onClick={() => handleDelete(ac.registration)}
                  className="text-red-600"
                >
                  Supprimer
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
