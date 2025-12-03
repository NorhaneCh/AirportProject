import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getHangar, updateHangar } from "../../api/hangars";

export default function HangarEdit() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [hangar, setHangar] = useState(null);

  useEffect(() => {
    getHangar(id).then(setHangar);
  }, [id]);

  if (!hangar) return <div className="p-4">Chargement...</div>;

  const handleChange = (e) => setHangar({ ...hangar, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    await updateHangar(id, hangar);
    navigate("/hangars");
  };

  return (
    <div className="p-6 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">Modifier le hangar</h1>
      <form className="space-y-4" onSubmit={handleSubmit}>
        <input
          type="number"
          name="capacite"
          value={hangar.capacite}
          onChange={handleChange}
          className="w-full border p-2"
          required
        />
        <select name="etat" value={hangar.etat} onChange={handleChange} className="w-full border p-2">
          <option value="VIDE">VIDE</option>
          <option value="PARTIELLEMENT_PLEIN">PARTIELLEMENT PLEIN</option>
          <option value="PLEIN">PLEIN</option>
          <option value="EN_MAINTENANCE">EN MAINTENANCE</option>
          <option value="INDISPONIBLE">INDISPONIBLE</option>
        </select>
        <button className="px-4 py-2 bg-green-600 text-white rounded">Enregistrer</button>
      </form>
    </div>
  );
}
