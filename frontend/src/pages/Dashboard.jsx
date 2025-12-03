import Layout from "../components/Layout";
import Card from "../components/Card";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
  const navigate = useNavigate();

  return (
    <Layout title="Gestion de l'Aéroport">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">

        <Card 
          title="Avions" 
          onClick={() => navigate("/aircraft")}
        />

        <Card 
          title="Hangars" 
          onClick={() => navigate("/hangars")}
        />

        <Card 
          title="Pistes" 
          onClick={() => navigate("/runways")}
        />

        <Card 
          title="Vols" 
          onClick={() => navigate("/flights")}
        />

      </div>
    </Layout>
  );
}
