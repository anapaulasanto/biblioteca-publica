import { useLocation } from "react-router-dom";
import BooksCardDetails from "../components/BooksCardDetails";

export default function BookDetails() {
    const location = useLocation();
    const book = location.state; 
    console.log(book);    

    return (
        <div>
            <BooksCardDetails book={book} />
        </div>
    )
}