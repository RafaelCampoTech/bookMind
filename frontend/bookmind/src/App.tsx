import './App.css'
import { Book, Search, Database, Users } from 'lucide-react';
import StatCard from './components/StatCard'

function App() {


  return (
    <>
      <div className="App">
        <StatCard
          label="Total Books"
          value="1234"
          sub="Indexed"
          trend="+12%"
          icon={<Users />}
        />
      </div>
    </>
  )
}

export default App
