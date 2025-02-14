import { useState } from 'react';
import { Container, Typography, TextField, Button } from '@mui/material';
import api from '../services/api';

const QueryInterface = () => {
  const [query, setQuery] = useState('');
  const [response, setResponse] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const result = await api.post('/llm/query', { query });
      setResponse(result.data.response);
    } catch (error) {
      console.error('Error processing query:', error);
    }
  };

  return (
    <Container maxWidth='md'>
      <Typography variant='h4'>Ask a Question</Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          fullWidth
          multiline
          rows={4}
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder='Enter your query...'
          sx={{ mb: 2 }}
        />
        <Button type='submit' variant='contained' color='primary'>
          Submit
        </Button>
        {response && (
          <>
            <Typography variant='h6' sx={{ mt: 2 }}>
              Response:
            </Typography>
            <Typography>{response}</Typography>
          </>
        )}
      </form>
    </Container>
  );
};

export default QueryInterface;
