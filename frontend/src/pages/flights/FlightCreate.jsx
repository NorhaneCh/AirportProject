import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { createFlight } from "../../api/flights";
import { getAllAircraft, updateAircraft } from "../../api/aircraft";
import { getAllPistes } from "../../api/runways";

export default function FlightCreate() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    numero: "",
    origine: "lino",
    destination: "",
    dateDepart: "",
    dateArrivee: "",
    statut: "PREVU",
    avionImmatriculation: "",
    pisteId: "",
    isExternal: false,
  });

  const [aircrafts, setAircrafts] = useState([]);
  const [pistes, setPistes] = useState([]);
  const [error, setError] = useState(null);

  // Récupérer les avions disponibles
  useEffect(() => {
    const fetchAircrafts = async () => {
      try {
        const data = await getAllAircraft();
        const available = data.filter((a) => a.etat === "DISPONIBLE");
        setAircrafts(available);
      } catch (err) {
        console.error(err);
        setError("Impossible de récupérer les avions.");
      }
    };
    fetchAircrafts();
  }, []);

  // Récupérer les pistes disponibles
  useEffect(() => {
    const fetchPistes = async () => {
      try {
        const data = await getAllPistes();
        const available = data.filter((p) => p.etat === "LIBRE");
        setPistes(available);
      } catch (err) {
        console.error(err);
        setError("Impossible de récupérer les pistes.");
      }
    };
    fetchPistes();
  }, []);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = { ...form, historiqueStatuts: [form.statut] };
      console.log("flight payload", payload);

      await createFlight(payload);

      // Mettre à jour l'état de l'avion sélectionné
      if (form.avionImmatriculation) {
        const selected = aircrafts.find(
          (a) => a.immatriculation === form.avionImmatriculation
        );
        if (selected) {
          await updateAircraft(form.avionImmatriculation, {
            ...selected,
            etat: "INDISPONIBLE",
          });
        }
      }

      navigate("/flights");
    } catch (err) {
      console.error(err);
      setError("Erreur lors de la création du vol.");
    }
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Créer un vol</h1>

      {error && <div className="text-red-600 mb-4">{error}</div>}

      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="destination"
          placeholder="Destination"
          value={form.destination}
          onChange={handleChange}
          className="w-full border p-2"
          required
        />

        <label>
          Date et heure de départ
          <input
            type="datetime-local"
            name="dateDepart"
            value={form.dateDepart}
            onChange={handleChange}
            className="w-full border p-2 mb-4 mt-1"
            required
          />
        </label>

        <label>
          Date et heure d'arrivée
          <input
            type="datetime-local"
            name="dateArrivee"
            value={form.dateArrivee}
            onChange={handleChange}
            className="w-full border p-2 mt-1"
            required
          />
        </label>

        <select
          name="avionImmatriculation"
          value={form.avionImmatriculation}
          onChange={handleChange}
          className="w-full border p-2 mt-4"
        >
          <option value="">Sélectionner un avion (facultatif)</option>
          {aircrafts.map((a) => (
            <option key={a.immatriculation} value={a.immatriculation}>
              {a.immatriculation} ({a.type}, {a.capacite} places)
            </option>
          ))}
        </select>

        <select
          name="pisteId"
          value={form.pisteId}
          onChange={handleChange}
          className="w-full border p-2 mt-2"
        >
          <option value="">Sélectionner une piste (facultatif)</option>
          {pistes.map((p) => (
            <option key={p.id} value={p.id}>
              Piste {p.id} ({p.longueur} m)
            </option>
          ))}
        </select>

        <button
          type="submit"
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition hover:cursor-pointer"
        >
          Créer
        </button>
      </form>
    </div>
  );
}
