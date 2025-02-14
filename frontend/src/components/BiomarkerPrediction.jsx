// import React from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper
} from '@mui/material';

const BiomarkerPrediction = ({ biomarkers }) => (
  <TableContainer component={Paper}>
    <Table>
      <TableHead>
        <TableRow>
          <TableCell>Biomarker</TableCell>
          <TableCell>Prediction</TableCell>
          <TableCell>Confidence</TableCell>
        </TableRow>
      </TableHead>
      <TableBody>
        {biomarkers.map((biomarker, index) => (
          <TableRow key={index}>
            <TableCell>{biomarker.name}</TableCell>
            <TableCell>{biomarker.prediction}</TableCell>
            <TableCell>{(biomarker.confidence * 100).toFixed(2)}%</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  </TableContainer>
);

export default BiomarkerPrediction;
