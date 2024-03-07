import {Box, AppBar, Toolbar, IconButton, Typography, Button, Container} from "@mui/material";
import {Link} from "react-router-dom";
import AccessibilityNewIcon from '@mui/icons-material/AccessibilityNew';
import {useState} from "react";
import { AllEventFounders} from "./EventFounders/allEventFounders";

export const AppMenu = () => {
    const [state, setState] = useState(0);

    return (
        <Container>
            <div style={{alignItems: "center", textAlign: "center"}}>
                <h1> Welcome to Record Label App  </h1>
            </div>
            {state === 0 && (
                <Box sx={{flexGrow: 1}}>
                    <AppBar position="static" sx={{marginBottom: "20px"}}>
                        <Toolbar>
                            <IconButton
                                component={Link}
                                to="/"
                                size="large"
                                edge="start"
                                color="inherit"
                                aria-label="school"
                                sx={{mr: 2}}>
                                <AccessibilityNewIcon/>
                            </IconButton>
                            <Typography variant="h6" component="div" sx={{mr: 5}}>
                                Artist Managers management:
                            </Typography>
                            <Button
                                to="/"
                                component={Link}
                                color="inherit"
                                sx={{mr: 5}}
                                startIcon={<AccessibilityNewIcon/>}
                                onClick={() => setState(1)}
                            >
                                Artist Managers
                            </Button>
                        </Toolbar>

                    </AppBar>
                </Box>
            )}

            {state === 1 && (
                <><Button
                    to="/"
                    component={Link}
                    color="inherit"
                    sx={{mr: 5}}
                    startIcon={<AccessibilityNewIcon/>}
                    onClick={() => setState(0)}
                >
                    Back
                </Button><AllEventFounders/></>
            )}
        </Container>
    );
};