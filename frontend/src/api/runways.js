const BASE_URL = import.meta.env.VITE_BASE_URL;
const API_URL = `${BASE_URL}/pistes`;

export async function getAllPistes() {
  const res = await fetch(API_URL);
  return res.json();
}

export async function getPiste(id) {
  const res = await fetch(`${API_URL}/${id}`);
  if (res.status === 404) return null;
  return res.json();
}

export async function createPiste(data) {
  const res = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      longueur: Number(data.length),
      etat: data.status.toUpperCase(),
    }),
  });

  if (!res.ok) {
    console.error("API ERROR:", await res.text());
    throw new Error("Erreur API");
  }

  return res.json();
}

export async function updatePiste(id, data) {
  const res = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({
      id: Number(id),
      longueur: Number(data.longueur),
      etat: data.etat,
    }),
  });

  if (!res.ok) {
    throw new Error("Erreur API lors de la mise à jour de la piste");
  }

  return res.json();
}

export async function deletePiste(id) {
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
}
