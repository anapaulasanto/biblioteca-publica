import axios from "axios"
import { DataApi } from "../data/DataApi"

async function handleView() {
    // const urlView = DataApi.urlView
    // let response = await axios.get(urlView)
    // let data = response.data.items
    // console.table(data)

    const url = 'http://localhost:8081/biblioteca-publica/livros/buscar?titulo=harry'
    let response = await axios.get(url)
    let data = response.data
    console.log(data);
    
}
handleView()

export default function BooksSection({ books }) {
    return (
        <section>
            <div>
                <div class="row d-flex justify-content-center mt-5">
                    {books.map((book, index) => (
                        <div key={index} class="card me-3 col-sm-6 mb-sm-0 col-md-2 col-lg-3 mb-lg-3" >
                            <div class="card-body">
                                <h5 className="card-title">{book.title}</h5>
                                <p className="card-text">{book.author}</p>
                                <p className="card-text">{book.price}</p>
                                <p className="card-text">{book.price}</p>
                                <a href="#" className="btn btn-primary">Download</a>
                            </div>
                        </div>
                    ))}
                </div>          
            </div>
        </section>

        
    )
}