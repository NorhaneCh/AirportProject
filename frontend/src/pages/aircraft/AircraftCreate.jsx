import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createAircraft } from "../../api/aircraft";

export default function AircraftCreate() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    registration: "",
    type: "",
    capacity: 0,
    status: "DISPONIBLE",
  });

  const updateField = (e) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: name === "capacity" ? Number(value) : value,
    });
  };

  const submit = async (e) => {
    e.preventDefault();
    try {
      await createAircraft(form);
      navigate("/aircraft");
    } catch (err) {
      console.error(err);
      alert("Erreur API lors de la création de l'avion");
    }
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Créer un avion</h1>

      <form onSubmit={submit} className="space-y-4">

        <input
          type="text"
          name="registration"
          placeholder="Immatriculation"
          className="w-full border p-2"
          onChange={updateField}
          required
        />

        <input
          type="text"
          name="type"
          placeholder="Type (ex : A320)"
          className="w-full border p-2"
          onChange={updateField}
          required
        />

        <input
          type="number"
          name="capacity"
          placeholder="Capacité"
          className="w-full border p-2"
          onChange={updateField}
          required
        />

        <select
          name="status"
          value={form.status}
          onChange={updateField}
          className="w-full border p-2"
        >
          <option value="DISPONIBLE">Disponible</option>
          <option value="EN_VOL">En vol</option>
          <option value="EN_MAINTENANCE">En maintenance</option>
          <option value="INDISPONIBLE">Indisponible</option>
        </select>

        <button className="px-4 py-2 bg-blue-600 text-white rounded">
          Créer
        </button>
      </form>
    </div>
  );
}
