import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createHangar } from "../../api/hangars";

export default function HangarCreate() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ capacite: 1, etat: "VIDE", avions: [] });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await createHangar(form);
    navigate("/hangars");
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Créer un hangar</h1>
      <form className="space-y-4" onSubmit={handleSubmit}>
        <input
          type="number"
          name="capacite"
          value={form.capacite}
          onChange={handleChange}
          className="w-full border p-2"
          required
        />
        <select name="etat" value={form.etat} onChange={handleChange} className="w-full border p-2">
          <option value="VIDE">VIDE</option>
          <option value="PARTIELLEMENT_PLEIN">PARTIELLEMENT PLEIN</option>
          <option value="PLEIN">PLEIN</option>
          <option value="EN_MAINTENANCE">EN MAINTENANCE</option>
          <option value="INDISPONIBLE">INDISPONIBLE</option>
        </select>
        <button className="px-4 py-2 bg-blue-600 text-white rounded">Créer</button>
      </form>
    </div>
  );
}
