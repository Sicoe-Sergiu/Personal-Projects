import {
    Button,
    CircularProgress,
    IconButton, Table, TableBody, TableCell,
    TableContainer, TableHead, TableRow,
    TextField,
    Tooltip,
    Paper
} from "@mui/material";
import { Container } from "@mui/system";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import { EventFounders } from "../../models/EventFounders";
import AddIcon from "@mui/icons-material/Add";
import EditIcon from "@mui/icons-material/Edit";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";

export const AllEventFounders = () => {
    const [loading, setLoading] = useState(false);
    const [founders, setFounders] = useState<EventFounders[]>([]);
    const [order, setOrder] = useState("asc");
    let[input, setInput] = useState<number | undefined>()
    const navigate = useNavigate();

    useEffect( () => {
        setLoading(true);
        fetch(`${BACKEND_API_URL}/event-founder/`)
            .then(async (response) => (await response.json()).data )
            .then((data) => {
                setFounders(data);
                setLoading(false)
            })
    }, []);

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

    let handleClick = () => {
        if (input !== undefined){
            navigate(`/event-founders-filter/${input}/`)
        }else {
            alert("Invalid Event Founder rating!")
        }
    }

    return(
        <Container>
            <h1> All Event Founders </h1>
            <div style={{ display: "flex", alignItems: "center", marginLeft: "700px", marginBottom: "-30px" }}>
            <TextField
                label="Founder rating..."
                onChange={(event) => {
						setInput( parseInt(event.target.value))}}
                InputProps={{ style: { color: "black" } }}
                InputLabelProps={{style: {color: 'darkgrey'}}}
                style={{ marginRight: "16px", color:'whitesmoke' }}
            />
            <Button onClick={handleClick} sx={{ mr: 3 }}  variant="contained" style={{color:"whitesmoke"}}>
                Filter
            </Button>
            </div>
            { loading && <CircularProgress/>}
            { !loading && founders.length === 0 && <p> No Founders found </p>}
            { !loading && (
                <IconButton component={Link} sx={{mr: 3}} to={`/event-founders/add/`}>
                    <Tooltip title="Add new founder" arrow>
                        <AddIcon color="primary"/>
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