import {
    CircularProgress,
    IconButton, Table, TableBody, TableCell,
    TableContainer, TableHead, TableRow,
    Tooltip,
    Paper
} from "@mui/material";
import { Container } from "@mui/system";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { EventFounders } from "../../models/EventFounders";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";

export const FilterFoundersByRating = () => {
    const [loading, setLoading] = useState(false);
    const [founders, setFounders] = useState<EventFounders[]>([]);
    const [order, setOrder] = useState("asc");
    const { input } = useParams();

    useEffect( () => {
        setLoading(true);
        fetch(`${BACKEND_API_URL}/founder-rating-filter/${input}/`)
            .then(async (response) => (await response.json()).data)
            .then((data) => {
                setFounders(data);
                setLoading(false)
            })
    }, [input]);

    const sorting = () => {
        if (order === "asc") {
            const sorted = [...founders].sort((founder1,founder2) =>
                    founder1.name.toLowerCase() > founder2.name.toLowerCase() ? 1 : -1
            );
            setFounders(sorted);
            setOrder("des");
        }
        if (order === "des") {
            const sorted = [...founders].sort((founder1,founder2) =>
                    founder1.name.toLowerCase() < founder2.name.toLowerCase() ? 1 : -1
            );
            setFounders(sorted);
            setOrder("asc");
        }
    }

    return(
        <Container>

            <h1> Event Founders with rating grater than {input} </h1>
            { loading && <CircularProgress/>}
            { !loading && founders.length === 0 && <p> No Founders found </p>}
            { !loading && (
                <IconButton component={Link} sx={{mr: 3}} to={`/event-founders/list/`}>

                    <Tooltip title="Back" arrow>
                        <ArrowBackIcon color="primary"/>
                    </Tooltip>
                </IconButton>
            )}

            { !loading && founders.length > 0 && (
                <TableContainer component={Paper}>
                    <Table sx={{minWidth: 650}} aria-label="simple table">
                        <TableHead>
                            <TableRow>
                                <TableCell>#</TableCell>
                                <TableCell onClick={() => sorting()} align="center">Name</TableCell>
                                <TableCell align="center">Rating</TableCell>
                                <TableCell align="center">Email</TableCell>
                                <TableCell align="center">Phone</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            { founders.map((founder, index) => (
                                <TableRow key={founder.id}>
                                    <TableCell component="th" scope="row">
                                        {index + 1}
                                    </TableCell>
                                    <TableCell component="th" scope="row" align="center">
                                        {founder.name}
                                    </TableCell>
                                    <TableCell component="th" scope="row" align="center">
                                        {founder.rating}
                                    </TableCell>
                                    <TableCell component="th" scope="row" align="center">
                                        {founder.email}
                                    </TableCell>
                                    <TableCell component="th" scope="row" align="center">
                                        {founder.phone}
                                    </TableCell>
                                    <TableCell align={"right"}>
                                        <IconButton component={Link} sx={{mr: 3}} to={`/event-founders/${founder.id}/edit`}>
                                            <EditIcon/>
                                        </IconButton>

                                        <IconButton component={Link} sx={{mr: 3}} to={`/event-founders/${founder.id}/delete`}>
                                            <DeleteForeverIcon sx={{color: "red"}}/>
                                        </IconButton>
                                    </TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )}
        </Container>
    );
}