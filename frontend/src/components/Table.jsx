export default function Table({ columns, data, actions }) {
  return (
    <table className="min-w-full bg-white shadow rounded">
      <thead className="bg-gray-200">
        <tr>
          {columns.map(col => (
            <th key={col} className="p-3 text-left font-bold">{col}</th>
          ))}
          {actions && <th className="p-3 font-bold">Actions</th>}
        </tr>
      </thead>

      <tbody>
        {data.map((row, idx) => (
          <tr key={idx} className="border-b hover:bg-gray-50">
            {Object.values(row).map((v, i) => (
              <td key={i} className="p-3">{v}</td>
            ))}

            {actions && (
              <td className="p-3 flex gap-2">
                {actions.map((action, i) => (
                  <button 
                    key={i} 
                    className="px-3 py-1 bg-blue-600 text-white rounded"
                    onClick={() => action.onClick(row.id)}
                  >
                    {action.label}
                  </button>
                ))}
              </td>
            )}
          </tr>
        ))}
      </tbody>
    </table>
  );
}
