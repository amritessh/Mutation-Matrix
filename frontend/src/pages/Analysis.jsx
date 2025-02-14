import { useState, useEffect } from 'react';
import {
  Container,
  Typography,
  CircularProgress,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Box
} from '@mui/material';
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer
} from 'recharts';
import api from '../services/api';

const Analysis = () => {
  const [analysisResults, setAnalysisResults] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchAnalysisResults = async () => {
      try {
        const response = await api.get('/analysis/results');
        setAnalysisResults(response.data);
      } catch (error) {
        console.error('Failed to fetch analysis results', error);
        setError('Failed to fetch analysis results. Please try again later.');
      } finally {
        setLoading(false);
      }
    };

    fetchAnalysisResults();
  }, []);

  if (loading) {
    return (
      <Container maxWidth='md' sx={{ textAlign: 'center', mt: 4 }}>
        <CircularProgress />
      </Container>
    );
  }

  if (error) {
    return (
      <Container maxWidth='md' sx={{ mt: 4 }}>
        <Typography color='error'>{error}</Typography>
      </Container>
    );
  }

  return (
    <Container maxWidth='lg'>
      <Typography variant='h2' component='h1' gutterBottom>
        Analysis Results
      </Typography>
      {analysisResults && (
        <>
          <Paper elevation={3} sx={{ p: 3, mb: 4 }}>
            <Typography variant='h5' gutterBottom>
              Variant Summary
            </Typography>
            <ResponsiveContainer width='100%' height={300}>
              <BarChart data={analysisResults.variantSummary}>
                <CartesianGrid strokeDasharray='3 3' />
                <XAxis dataKey='type' />
                <YAxis />
                <Tooltip />
                <Legend />
                <Bar dataKey='count' fill='#8884d8' />
              </BarChart>
            </ResponsiveContainer>
          </Paper>

          <Paper elevation={3} sx={{ p: 3, mb: 4 }}>
            <Typography variant='h5' gutterBottom>
              Top Variants
            </Typography>
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>Position</TableCell>
                    <TableCell>Reference</TableCell>
                    <TableCell>Alternate</TableCell>
                    <TableCell>Type</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {analysisResults.topVariants.map((variant, index) => (
                    <TableRow key={index}>
                      <TableCell>{variant.position}</TableCell>
                      <TableCell>{variant.reference}</TableCell>
                      <TableCell>{variant.alternate}</TableCell>
                      <TableCell>{variant.type}</TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Paper>

          <Paper elevation={3} sx={{ p: 3 }}>
            <Typography variant='h5' gutterBottom>
              Biomarker Predictions
            </Typography>
            <TableContainer>
              <Table>
                <TableHead>
                  <TableRow>
                    <TableCell>Biomarker</TableCell>
                    <TableCell>Prediction</TableCell>
                    <TableCell>Confidence</TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {analysisResults.biomarkers.map((biomarker, index) => (
                    <TableRow key={index}>
                      <TableCell>{biomarker.name}</TableCell>
                      <TableCell>{biomarker.prediction}</TableCell>
                      <TableCell>
                        {(biomarker.confidence * 100).toFixed(2)}%
                      </TableCell>
                    </TableRow>
                  ))}
                </TableBody>
              </Table>
            </TableContainer>
          </Paper>
        </>
      )}
    </Container>
  );
};

export default Analysis;
