import express from "express";
import mysql,{Pool} from "promise-mysql";
import dotenv from 'dotenv';

export const router=express.Router();
let pool:Pool;
dotenv.config();

(async function (){
    pool=await mysql.createPool({
        host:'localhost',
        port:+process.env.port!,
        user:process.env.username,
        password:process.env.password,
        database:process.env.database,
        connectionLimit:+process.env.connection_limit!
    })
})();

type Book={
    isbn:string,
    title:string,
}
router.get("/",async (req, res)=>{
    const books = await pool.query('SELECT * FROM book');
    res.json(books);
});

router.delete("/:bookId",async (req, res)=>{
    const {bookId} = req.params;
    const result = await pool.query('DELETE FROM book WHERE isbn=?',[bookId]);
    res.sendStatus(result.affectedRows?204:404);
});

router.patch("/:bookId",async (req, res)=>{
    const book=(req.body as Book);
    const {bookId}=req.params;
    book.isbn=bookId;
    if (!book.title){
        res.sendStatus(400);
        return;
    }
    const result = await pool.query('UPDATE book SET title=? WHERE isbn=?',[book.title,book.isbn]);
    res.sendStatus(result.affectedRows?204:404);
})