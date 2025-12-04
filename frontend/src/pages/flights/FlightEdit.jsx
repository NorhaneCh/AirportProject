import { useState, useEffect } from "react";
import { updateFlight, getFlightById } from "../../api/flights";

export default function FlightEdit({ flightId, onSuccess }) {
  const [flight, setFlight] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Charger le vol au montage
  useEffect(() => {
    async function fetchFlight() {
      try {
        const data = await getFlightById(flightId);
        setFlight(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    }
    fetchFlight();
  }, [flightId]);

  if (loading) return <p>Chargement...</p>;
  if (error) return <p>Erreur: {error}</p>;
  if (!flight) return <p>Vol introuvable</p>;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFlight((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const updated = await updateFlight(flightId, flight);
      onSuccess(updated); // callback pour notifier le parent
      alert("Vol mis à jour avec succès !");
    } catch (err) {
      alert("Erreur lors de la mise à jour : " + err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Origine:</label>
        <input
          type="text"
          name="origine"
          value={flight.origine}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Destination:</label>
        <input
          type="text"
          name="destination"
          value={flight.destination}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Date de départ:</label>
        <input
          type="datetime-local"
          name="dateDepart"
          value={flight.dateDepart}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Date d'arrivée:</label>
        <input
          type="datetime-local"
          name="dateArrivee"
          value={flight.dateArrivee}
          onChange={handleChange}
        />
      </div>
      <button type="submit">Modifier le vol</button>
    </form>
  );
}
