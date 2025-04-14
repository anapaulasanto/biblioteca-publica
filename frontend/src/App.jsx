import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Books from './pages/Books';

function App() {

  return (
    <>
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/books" element={<Books />} />
          </Routes>
        </div>
      </BrowserRouter>
    </>
  )
}

export default App
