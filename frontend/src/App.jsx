import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Books from './pages/Books';
import BookDetails from './pages/BookDetails';

function App() {

  return (
    <>
      <BrowserRouter>
        <div>
          <Routes>
            <Route path="/" element={<Books />} />
            <Route path="book/:slug" element={<BookDetails />} />
          </Routes>
        </div>
      </BrowserRouter>
    </>
  )
}

export default App
