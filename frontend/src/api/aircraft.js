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
      capacite: Number(data.capacity),
      etat: data.status,
    }),
  });
  return res.json();
}

export async function updateAircraft(registration, data) {
  const res = await fetch(`${API_URL}/${registration}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      immatriculation: registration,
      type: data.type,
      capacite: Number(data.capacity),
      etat: data.status,
    }),
  });
  return res.json();
}

export async function deleteAircraft(registration) {
  await fetch(`${API_URL}/${registration}`, { method: "DELETE" });
}
