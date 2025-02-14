import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Footer from './components/Footer';
import Home from './pages/Home';
import Upload from './pages/Upload';
import Analysis from './pages/Analysis';
import Reports from './pages/Reports';
import Query from './pages/Query';

const App = () => (
  <Router>
    <div
      className='App'
      style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}
    >
      <Header />
      <main style={{ flexGrow: 1, padding: '2rem' }}>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/upload' element={<Upload />} />
          <Route path='/analysis' element={<Analysis />} />
          <Route path='/reports' element={<Reports />} />
          <Route path='/query' element={<Query />} />
        </Routes>
      </main>
      <Footer />
    </div>
  </Router>
);

export default App;
