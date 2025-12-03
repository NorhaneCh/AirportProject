import { lazy, Suspense } from "react";
import { Routes, Route, Navigate } from "react-router-dom";

// Dashboard
const Dashboard = lazy(() => import("./pages/Dashboard"));

// Aircraft
const AircraftList = lazy(() => import("./pages/aircraft/AircraftList"));
const AircraftCreate = lazy(() => import("./pages/aircraft/AircraftCreate"));
const AircraftEdit = lazy(() => import("./pages/aircraft/AircraftEdit"));
const AircraftDetails = lazy(() => import("./pages/aircraft/AircraftDetail"));

// Hangars
const HangarList = lazy(() => import("./pages/hangars/HangarList"));
const HangarCreate = lazy(() => import("./pages/hangars/HangarCreate"));
const HangarEdit = lazy(() => import("./pages/hangars/HangarEdit"));
const HangarDetails = lazy(() => import("./pages/hangars/HangarDetail"));

// Runways
const RunwayList = lazy(() => import("./pages/runways/RunwayList"));
const RunwayCreate = lazy(() => import("./pages/runways/RunwayCreate"));
const RunwayEdit = lazy(() => import("./pages/runways/RunwayEdit"));
const RunwayDetails = lazy(() => import("./pages/runways/RunwayDetail"));

// Flights
const FlightList = lazy(() => import("./pages/flights/FlightList"));
const FlightCreate = lazy(() => import("./pages/flights/FlightCreate"));
const FlightEdit = lazy(() => import("./pages/flights/FlightEdit"));
const FlightDetails = lazy(() => import("./pages/flights/FlightDetail"));

function App() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Suspense fallback={<div className="p-4">Chargement...</div>}>
        <Routes>
          {/* Dashboard */}
          <Route path="/" element={<Dashboard />} />

          {/* Aircraft */}
          <Route path="/aircraft" element={<AircraftList />} />
          <Route path="/aircraft/new" element={<AircraftCreate />} />
          <Route path="/aircraft/:id" element={<AircraftDetails />} />
          <Route path="/aircraft/:id/edit" element={<AircraftEdit />} />

          {/* Hangars */}
          <Route path="/hangars" element={<HangarList />} />
          <Route path="/hangars/new" element={<HangarCreate />} />
          <Route path="/hangars/:id" element={<HangarDetails />} />
          <Route path="/hangars/:id/edit" element={<HangarEdit />} />

          {/* Runways */}
          <Route path="/runways" element={<RunwayList />} />
          <Route path="/runways/new" element={<RunwayCreate />} />
          <Route path="/runways/:id" element={<RunwayDetails />} />
          <Route path="/runways/:id/edit" element={<RunwayEdit />} />

          {/* Flights */}
          <Route path="/flights" element={<FlightList />} />
          <Route path="/flights/new" element={<FlightCreate />} />
          <Route path="/flights/:id" element={<FlightDetails />} />
          <Route path="/flights/:id/edit" element={<FlightEdit />} />

          {/* Redirection */}
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </Suspense>
    </div>
  );
}

export default App;
