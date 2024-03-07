import React from 'react';
import './App.css';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import {AppMenu} from "./components/AppMenu";
import {AddEventFounder} from "./components/EventFounders/addEventFounder";
import {EditEventFounder} from "./components/EventFounders/editEventFounder";
import {DeleteEventFounder} from "./components/EventFounders/deleteEventFounder";
import {AllEventFounders} from "./components/EventFounders/allEventFounders";
import {FilterFoundersByRating} from "./components/filter/filterFoundersByRating";

function App() {
  return (
        <React.Fragment>
			<Router>
				<Routes>
					<Route path="/" element={<AppMenu />} />
					<Route path="/event-founders/" element={<AppMenu />} />
					<Route path="/event-founders/list/" element={<AllEventFounders />} />
					<Route path="/event-founders/add/" element={<AddEventFounder />} />
					<Route path="/event-founders/:founderID/edit/" element={<EditEventFounder />} />
					<Route path="/event-founders/:founderID/delete/" element={<DeleteEventFounder />} />

					<Route path="/event-founders-filter/:input/" element={<FilterFoundersByRating />} />
				</Routes>
			</Router>
		</React.Fragment>
    )
}

export default App;
