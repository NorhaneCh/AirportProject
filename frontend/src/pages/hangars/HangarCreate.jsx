import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createHangar } from "../../api/hangars";

export default function HangarCreate() {
  const navigate = useNavigate();
  const [capacite, setCapacite] = useState(0);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createHangar({ capacite });
      navigate("/hangars");
    } catch (err) {
      setError("Erreur lors de la création du hangar.");
    }
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Créer un hangar</h1>

      {error && <div className="text-red-600 mb-4">{error}</div>}

      <form className="space-y-4" onSubmit={handleSubmit}>
        <label className="block">
          Capacité
          <input
            type="number"
            name="capacite"
            value={capacite}
            onChange={(e) => setCapacite(Number(e.target.value))}
            className="w-full border p-2 mt-1"
            min={1}
            required
          />
        </label>
        <button
          type="submit"
          className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition"
        >
          Créer
        </button>
      </form>
    </div>
  );
}
