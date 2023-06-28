"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.router = void 0;
const express_1 = __importDefault(require("express"));
const promise_mysql_1 = __importDefault(require("promise-mysql"));
const dotenv_1 = __importDefault(require("dotenv"));
exports.router = express_1.default.Router();
let pool;
dotenv_1.default.config();
(function () {
    return __awaiter(this, void 0, void 0, function* () {
        pool = yield promise_mysql_1.default.createPool({
            host: 'localhost',
            port: +process.env.port,
            user: process.env.username,
            password: process.env.password,
            database: process.env.database,
            connectionLimit: +process.env.connection_limit
        });
    });
})();
exports.router.delete("/:bookId", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { bookId } = req.params;
    const result = yield pool.query('DELETE FROM book WHERE isbn=?', [bookId]);
    res.sendStatus(result.affectedRows ? 204 : 404);
}));
exports.router.patch("/:bookId", (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const book = req.body;
    const { bookId } = req.params;
    book.isbn = bookId;
    if (!book.title) {
        res.sendStatus(400);
        return;
    }
    const result = yield pool.query('UPDATE book SET title=? WHERE isbn=?', [book.title, book.isbn]);
    res.sendStatus(result.affectedRows ? 204 : 404);
}));
