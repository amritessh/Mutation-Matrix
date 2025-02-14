import { useState, useEffect } from 'react';
import {
  Container,
  Typography,
  Button,
  CircularProgress,
  List,
  ListItem,
  ListItemText,
  ListItemSecondaryAction,
  Paper
} from '@mui/material';
import { GetApp } from '@mui/icons-material';
import api from '../services/api';

const Reports = () => {
  const [reports, setReports] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchReports = async () => {
      try {
        const response = await api.get('/reports');
        setReports(response.data);
      } catch (error) {
        console.error('Failed to fetch reports', error);
        setError('Failed to fetch reports. Please try again later.');
      } finally {
        setLoading(false);
      }
    };

    fetchReports();
  }, []);

  const handleDownload = async (reportId) => {
    try {
      const response = await api.get(`/reports/${reportId}/download`, {
        responseType: 'blob'
      });
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement('a');
      link.href = url;
      link.setAttribute('download', `report_${reportId}.pdf`);
      document.body.appendChild(link);
      link.click();
    } catch (error) {
      console.error('Failed to download report', error);
      alert('Failed to download report. Please try again.');
    }
  };

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
    <Container maxWidth='md'>
      <Typography variant='h2' component='h1' gutterBottom>
        Reports
      </Typography>
      <Paper elevation={3} sx={{ p: 3 }}>
        <List>
          {reports.map((report) => (
            <ListItem key={report.id}>
              <ListItemText
                primary={`Report ${report.id}`}
                secondary={new Date(report.createdAt).toLocaleString()}
              />
              <ListItemSecondaryAction>
                <Button
                  startIcon={<GetApp />}
                  onClick={() => handleDownload(report.id)}
                >
                  Download
                </Button>
              </ListItemSecondaryAction>
            </ListItem>
          ))}
        </List>
      </Paper>
    </Container>
  );
};

export default Reports;
