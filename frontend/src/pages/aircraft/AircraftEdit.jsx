import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getAircraft, updateAircraft } from "../api/aircraft";

export default function AircraftEdit() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [aircraft, setAircraft] = useState(null);

  useEffect(() => {
    getAircraft(id).then((a) => {
      setAircraft({
        registration: a.immatriculation,
        type: a.type,
        capacity: a.capacite,
        status: a.etat,
      });
    });
  }, [id]);

  if (!aircraft) return <div className="p-4">Chargement...</div>;

  const updateField = (e) => {
    setAircraft({ ...aircraft, [e.target.name]: e.target.value });
  };

  const submit = async (e) => {
    e.preventDefault();
    await updateAircraft(id, aircraft);
    navigate("/aircraft");
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Modifier un avion</h1>

      <form onSubmit={submit} className="space-y-4">
        <input
          type="text"
          name="registration"
          value={aircraft.registration}
          className="w-full border p-2"
          disabled
        />

        <input
          type="text"
          name="type"
          value={aircraft.type}
          onChange={updateField}
          className="w-full border p-2"
          required
        />

        <input
          type="number"
          name="capacity"
          value={aircraft.capacity}
          onChange={updateField}
          className="w-full border p-2"
          required
        />

        <select
          name="status"
          value={aircraft.status}
          onChange={updateField}
          className="w-full border p-2"
        >
          <option value="DISPONIBLE">Disponible</option>
          <option value="EN_VOL">En vol</option>
          <option value="EN_MAINTENANCE">En maintenance</option>
          <option value="INDISPONIBLE">Indisponible</option>
        </select>

        <button className="px-4 py-2 bg-green-600 text-white rounded">
          Enregistrer
        </button>
      </form>
    </div>
  );
}
