const BASE_URL = import.meta.env.VITE_BASE_URL;
const API_URL = `${BASE_URL}/avions`;

export async function getAllAircraft() {
  const res = await fetch(API_URL);
  return res.json();
}

export async function getAircraft(registration) {
  const res = await fetch(`${API_URL}/${registration}`);
  if (res.status === 404) return null;
  return res.json();
}

export async function createAircraft(data) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      immatriculation: data.registration,
      type: data.type,
      capacite: data.capacity,
      etat: data.status,
    }),
  });

  const text = await res.text();
  if (!res.ok) {
    console.error("Backend error:", text);
    throw new Error("Erreur API lors de la création de l'avion");
  }

  return JSON.parse(text);
}

export async function updateAircraft(registration, data) {
  const res = await fetch(`${API_URL}/${registration}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      immatriculation: registration,
      type: data.type,
      capacite: Number(data.capacite),
      etat: data.etat,
    }),
  });

  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Failed to update aircraft ${registration}: ${errText}`);
  }

  return res.json();
}

export async function deleteAircraft(registration) {
  await fetch(`${API_URL}/${registration}`, { method: "DELETE" });
}
