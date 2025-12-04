const BASE_URL = import.meta.env.VITE_BASE_URL;
const API_URL = `${BASE_URL}/vols`;

export async function getAllFlights() {
  const res = await fetch(API_URL);
  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Failed to fetch flights: ${errText}`);
  }
  return res.json();
}

// Récupérer un vol par son ID
export async function getFlightById(id) {
  const res = await fetch(`${API_URL}/${id}`);
  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Failed to fetch flight ${id}: ${errText}`);
  }
  return res.json();
}

// Créer un nouveau vol
export async function createFlight(data) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      numero: data.numero,
      origine: data.origine,
      destination: data.destination,
      dateDepart: data.dateDepart,
      dateArrivee: data.dateArrivee,
      avionImmatriculation: data.avionImmatriculation,
      pisteId: data.pisteId ? Number(data.pisteId) : null,
      historiqueStatuts: data.historiqueStatuts,
      isExternal: data.isExternal,
      statut: data.statut,
    }),
  });
  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Failed to create flight: ${errText}`);
  }

  return res.json();
}

// Mettre à jour un vol existant
export async function updateflight(id, data) {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Failed to update aircraft ${id}: ${errText}`);
  }
  return res.json();
}

// Supprimer un vol
export async function deleteFlight(id) {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });

  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Failed to delete flight ${id}: ${errText}`);
  }

  return true;
}
