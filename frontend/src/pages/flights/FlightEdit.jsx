import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";

export default function FlightEdit() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [flight, setFlight] = useState(null);

  useEffect(() => {
    setFlight({
      id,
      number: "AF123",
      origin: "Paris",
      destination: "New York",
      schedule: "10:00",
      status: "Prévu",
      aircraft: "F-ABCD"
    });
  }, [id]);

  if (!flight) return <div className="p-4">Chargement...</div>;

  const handleChange = (e) => setFlight({ ...flight, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Vol modifié (temporaire) :", flight);
    navigate("/flights");
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Modifier un vol</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input type="text" name="number" value={flight.number} onChange={handleChange} className="w-full border p-2" required />
        <input type="text" name="origin" value={flight.origin} onChange={handleChange} className="w-full border p-2" required />
        <input type="text" name="destination" value={flight.destination} onChange={handleChange} className="w-full border p-2" required />
        <input type="time" name="schedule" value={flight.schedule} onChange={handleChange} className="w-full border p-2" required />
        <select name="status" value={flight.status} onChange={handleChange} className="w-full border p-2">
          <option>Prévu</option>
          <option>En attente</option>
          <option>Embarquement</option>
          <option>Décollé</option>
          <option>En vol</option>
          <option>Arrivé</option>
          <option>Annulé</option>
        </select>
        <input type="text" name="aircraft" value={flight.aircraft} onChange={handleChange} className="w-full border p-2" placeholder="Avion assigné" />
        <button className="px-4 py-2 bg-green-600 text-white rounded">Enregistrer</button>
      </form>
    </div>
  );
}
