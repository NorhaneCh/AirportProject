export default function Card({ title, value, onClick }) {
  return (
    <div 
      onClick={onClick}
      className="cursor-pointer bg-white shadow rounded-lg p-6 hover:shadow-lg transition"
    >
      <h2 className="text-lg font-semibold">{title}</h2>
      {value && <p className="text-3xl font-bold mt-2">{value}</p>}
    </div>
  );
}
