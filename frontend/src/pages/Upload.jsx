import { useState } from 'react';
import {
  Container,
  Typography,
  Button,
  LinearProgress,
  Box,
  Paper,
  Alert
} from '@mui/material';
import { CloudUpload } from '@mui/icons-material';
import api from '../services/api';

const Upload = () => {
  const [file, setFile] = useState(null);
  const [uploading, setUploading] = useState(false);
  const [uploadProgress, setUploadProgress] = useState(0);
  const [uploadStatus, setUploadStatus] = useState(null);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    setUploading(true);
    setUploadStatus(null);
    try {
      await api.post('/upload', formData, {
        onUploadProgress: (progressEvent) => {
          const percentCompleted = Math.round(
            (progressEvent.loaded * 100) / progressEvent.total
          );
          setUploadProgress(percentCompleted);
        }
      });
      setUploadStatus({
        severity: 'success',
        message: 'Upload successful! Your genome is being processed.'
      });
    } catch (error) {
      console.error('Upload failed', error);
      setUploadStatus({
        severity: 'error',
        message: 'Upload failed. Please try again.'
      });
    } finally {
      setUploading(false);
    }
  };

  return (
    <Container maxWidth='md'>
      <Typography variant='h2' component='h1' gutterBottom>
        Upload Your Genome
      </Typography>
      <Paper elevation={3} sx={{ p: 4, mt: 4 }}>
        <form onSubmit={handleSubmit}>
          <input
            accept='.fastq,.bam,.vcf'
            style={{ display: 'none' }}
            id='raised-button-file'
            type='file'
            onChange={handleFileChange}
          />
          <label htmlFor='raised-button-file'>
            <Button
              variant='outlined'
              component='span'
              startIcon={<CloudUpload />}
            >
              Select Genome File
            </Button>
          </label>
          {file && (
            <Typography variant='body1' sx={{ mt: 2 }}>
              Selected file: {file.name}
            </Typography>
          )}
          <Button
            type='submit'
            variant='contained'
            color='primary'
            disabled={!file || uploading}
            sx={{ mt: 2 }}
          >
            Upload and Analyze
          </Button>
        </form>
        {uploading && (
          <Box sx={{ mt: 2 }}>
            <LinearProgress variant='determinate' value={uploadProgress} />
            <Typography variant='body2' color='text.secondary' align='center'>
              {`${uploadProgress}% Uploaded`}
            </Typography>
          </Box>
        )}
        {uploadStatus && (
          <Alert severity={uploadStatus.severity} sx={{ mt: 2 }}>
            {uploadStatus.message}
          </Alert>
        )}
      </Paper>
    </Container>
  );
};

export default Upload;
