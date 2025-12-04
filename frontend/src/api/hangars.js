const BASE_URL = import.meta.env.VITE_BASE_URL;
const API_URL = `${BASE_URL}/hangars`;

export async function getAllHangars() {
  const res = await fetch(API_URL);
  return res.json();
}

export async function getHangar(id) {
  const res = await fetch(`${API_URL}/${id}`);
  if (res.status === 404) return null;
  return res.json();
}

export async function createHangar(data) {
  const payload = {
    ...data,
    etat: "VIDE",
    avions: [],
  };
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (!res.ok) {
    const errText = await res.text();
    throw new Error(`Failed to create hangar: ${errText}`);
  }

  return res.json();
}

export async function updateHangar(id, data) {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      id,
      capacite: Number(data.capacite),
      etat: data.etat,
      avions: data.avions || [],
    }),
  });
  return res.json();
}

export async function deleteHangar(id) {
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
}

export async function addAvionToHangar(hangarId, avion) {
  const res = await fetch(`${API_URL}/${hangarId}/avions`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(avion),
  });
  return res.json();
}

export async function removeAvionFromHangar(hangarId, avion) {
  await fetch(`${API_URL}/${hangarId}/avions`, {
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(avion),
  });
}
