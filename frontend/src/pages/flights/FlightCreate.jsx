import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function FlightCreate() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    number: "",
    origin: "",
    destination: "",
    schedule: "",
    status: "Prévu",
    aircraft: ""
  });

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Nouveau vol (temporaire) :", form);
    navigate("/flights");
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Créer un vol</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input type="text" name="number" placeholder="Numéro du vol" value={form.number} onChange={handleChange} className="w-full border p-2" required />
        <input type="text" name="origin" placeholder="Origine" value={form.origin} onChange={handleChange} className="w-full border p-2" required />
        <input type="text" name="destination" placeholder="Destination" value={form.destination} onChange={handleChange} className="w-full border p-2" required />
        <input type="time" name="schedule" placeholder="Horaire" value={form.schedule} onChange={handleChange} className="w-full border p-2" required />
        <select name="status" value={form.status} onChange={handleChange} className="w-full border p-2">
          <option>Prévu</option>
          <option>En attente</option>
          <option>Embarquement</option>
          <option>Décollé</option>
          <option>En vol</option>
          <option>Arrivé</option>
          <option>Annulé</option>
        </select>
        <input type="text" name="aircraft" placeholder="Avion assigné (immatriculation)" value={form.aircraft} onChange={handleChange} className="w-full border p-2" />
        <button className="px-4 py-2 bg-blue-600 text-white rounded">Créer</button>
      </form>
    </div>
  );
}
