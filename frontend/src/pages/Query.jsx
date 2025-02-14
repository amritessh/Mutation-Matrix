import { useState } from 'react';
import {
  Container,
  Typography,
  TextField,
  Button,
  CircularProgress,
  Paper
} from '@mui/material';
import api from '../services/api';

const Query = () => {
  const [query, setQuery] = useState('');
  const [response, setResponse] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);
    try {
      const result = await api.post('/llm/query', { query });
      setResponse(result.data.response);
    } catch (error) {
      console.error('Query failed', error);
      setError(
        'An error occurred while processing your query. Please try again.'
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container maxWidth='md'>
      <Typography variant='h2' component='h1' gutterBottom>
        Natural Language Query
      </Typography>
      <Paper elevation={3} sx={{ p: 3 }}>
        <form onSubmit={handleSubmit}>
          <TextField
            fullWidth
            multiline
            rows={4}
            variant='outlined'
            label='Enter your query'
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            sx={{ mb: 2 }}
          />
          <Button
            type='submit'
            variant='contained'
            color='primary'
            disabled={!query || loading}
          >
            Submit Query
          </Button>
        </form>
        {loading && <CircularProgress sx={{ mt: 2 }} />}
        {error && (
          <Typography color='error' sx={{ mt: 2 }}>
            {error}
          </Typography>
        )}
        {response && (
          <Paper elevation={1} sx={{ mt: 2, p: 2 }}>
            <Typography variant='h6' gutterBottom>
              Response:
            </Typography>
            <Typography variant='body1'>{response}</Typography>
          </Paper>
        )}
      </Paper>
    </Container>
  );
};

export default Query;
