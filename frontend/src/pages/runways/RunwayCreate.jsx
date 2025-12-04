import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createPiste } from "../../api/runways";

export default function RunwayCreate() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ name: "", length: "", status: "LIBRE" });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await createPiste(form);
    navigate("/runways");
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Créer une piste</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="number"
          name="length"
          placeholder="Longueur (m)"
          value={form.length}
          onChange={handleChange}
          className="w-full border p-2"
          required
        />
        <select
          name="status"
          value={form.status}
          onChange={handleChange}
          className="w-full border p-2"
        >
          <option value="LIBRE">Libre</option>
          <option value="OCCUPEE">Occupée</option>
          <option value="EN_MAINTENANCE">Maintenance</option>
        </select>
        <button className="px-4 py-2 bg-blue-600 text-white rounded hover:cursor-pointer">
          Créer
        </button>
      </form>
    </div>
  );
}
