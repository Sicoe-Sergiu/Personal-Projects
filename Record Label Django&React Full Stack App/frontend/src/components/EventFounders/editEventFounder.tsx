import { Button, Card, CardContent, IconButton, TextField } from "@mui/material";
import { Container } from "@mui/system";
import {  useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { BACKEND_API_URL } from "../../constants";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import axios from "axios";
import { EventFounders } from "../../models/EventFounders";

export const EditEventFounder = () => {
    const { founderID } = useParams();
    const navigate = useNavigate();

    const [eventFounder, setFounder] = useState<EventFounders>({
        id: (typeof founderID === "string" ? parseInt(founderID) : -1),
        name: "",
        rating: 0,
        email: "",
        phone: "",
    });

    const editEventFounder = async (event: { preventDefault: () => void}) => {
        event.preventDefault();
        try {
            await axios.patch(`${BACKEND_API_URL}/event-founder/${founderID}/`, eventFounder, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            navigate("/event-founders/list/");
        }catch (error){
            console.log(error);
        }

    };
    return(
        <Container>
            <Card>
                <CardContent>
                    <IconButton component={Link} sx={{mr: 3}} to={`/event-founders/list/`}>
                        <ArrowBackIcon/>
                    </IconButton>{" "}
                    <form onSubmit={ editEventFounder }>

                        <TextField
                            id="name"
                            label="Name"
                            variant="outlined"
                            fullWidth
                            sx={{mb: 2}}
                            onChange={(event) => setFounder({...eventFounder, name: event.target.value})}
                        />

                        <TextField
                            id="rating"
                            label="Rating"
                            variant="outlined"
                            fullWidth
                            sx={{mb: 2}}
                            onChange={(event) => setFounder({...eventFounder, rating: +event.target.value})}
                        />

                        <TextField
                            id="email"
                            label="Email"
                            variant="outlined"
                            fullWidth
                            sx={{mb: 2}}
                            onChange={(event) => setFounder({...eventFounder, email: event.target.value})}
                        />

                        <TextField
                            id="phone"
                            label="Phone"
                            variant="outlined"
                            fullWidth
                            sx={{mb: 2}}
                            onChange={(event) => setFounder({...eventFounder, phone: event.target.value})}
                        />
                        <Button type="submit">Update Event Founder</Button>
                    </form>
                </CardContent>
            </Card>
        </Container>
    );
};