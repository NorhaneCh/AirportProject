export default function Layout({ title, children }) {
  return (
    <div className="min-h-screen bg-gray-100">
      <header className="bg-white shadow p-4">
        <h1 className="text-2xl font-bold">{title}</h1>
      </header>

      <main className="p-6">
        {children}
      </main>
    </div>
  );
}
