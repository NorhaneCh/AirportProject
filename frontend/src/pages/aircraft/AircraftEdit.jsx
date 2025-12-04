import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getAircraft, updateAircraft } from "../../api/aircraft";

const STATUS_OPTIONS = [
  { value: "DISPONIBLE", label: "Disponible" },
  { value: "EN_VOL", label: "En vol" },
  { value: "EN_MAINTENANCE", label: "En maintenance" },
  { value: "INDISPONIBLE", label: "Indisponible" },
];

export default function AircraftEdit() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [aircraft, setAircraft] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    async function fetchAircraft() {
      try {
        const a = await getAircraft(id);
        setAircraft({
          registration: a.immatriculation,
          type: a.type,
          capacity: a.capacite,
          status: a.etat,
        });
      } catch (err) {
        setError("Impossible de charger l'avion.");
      } finally {
        setLoading(false);
      }
    }

    fetchAircraft();
  }, [id]);

  const updateField = (e) => {
    const { name, value } = e.target;

    setAircraft((prev) => ({
      ...prev,
      [name]: name === "capacity" ? (value ? parseInt(value, 10) : 0) : value,
    }));
  };

  const submit = async (e) => {
    e.preventDefault();
    try {
      await updateAircraft(id, {
        immatriculation: aircraft.registration,
        type: aircraft.type,
        capacite: aircraft.capacity,
        etat: aircraft.status,
      });
      navigate("/aircraft");
    } catch (err) {
      setError("Erreur lors de la mise à jour de l'avion.");
    }
  };

  if (loading) return <div className="p-4">Chargement...</div>;
  if (error) return <div className="p-4 text-red-600">{error}</div>;
  if (!aircraft) return null;

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">{`Modifier l'avion ${id}`}</h1>

      <form onSubmit={submit} className="space-y-4">
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
          min={0}
          required
        />

        <select
          name="status"
          value={aircraft.status}
          onChange={updateField}
          className="w-full border p-2"
        >
          {STATUS_OPTIONS.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </select>

        <button
          type="submit"
          className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700 transition"
        >
          Enregistrer
        </button>
      </form>
    </div>
  );
}
