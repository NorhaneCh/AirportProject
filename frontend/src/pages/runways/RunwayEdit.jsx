import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getPiste, updatePiste } from "../../api/pistes";

export default function RunwayEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [runway, setRunway] = useState(null);

  useEffect(() => {
    getPiste(id).then(setRunway);
  }, [id]);

  if (!runway) return <div className="p-4">Chargement...</div>;

  const handleChange = (e) => setRunway({ ...runway, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await updatePiste(id, runway);
    navigate("/runways");
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Modifier la piste</h1>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input type="text" name="name" value={runway.nom || runway.name} onChange={handleChange} className="w-full border p-2" required />
        <input type="number" name="length" value={runway.longueur} onChange={handleChange} className="w-full border p-2" required />
        <select name="status" value={runway.etat} onChange={handleChange} className="w-full border p-2">
          <option value="LIBRE">Libre</option>
          <option value="OCCUPEE">Occupée</option>
          <option value="EN_MAINTENANCE">Maintenance</option>
        </select>
        <button className="px-4 py-2 bg-green-600 text-white rounded">Enregistrer</button>
      </form>
    </div>
  );
}
