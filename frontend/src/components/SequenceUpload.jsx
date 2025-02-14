import { useState } from 'react';
import { Container, Typography, Button, LinearProgress } from '@mui/material';
import api from '../services/api';

const SequenceUpload = () => {
  const [file, setFile] = useState(null);
  const [uploading, setUploading] = useState(false);
  const [uploadProgress, setUploadProgress] = useState(0);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) return;

    const formData = new FormData();
    formData.append('file', file);

    setUploading(true);
    try {
      await api.post('/upload', formData, {
        onUploadProgress: (progressEvent) => {
          const percentCompleted = Math.round(
            (progressEvent.loaded * 100) / progressEvent.total
          );
          setUploadProgress(percentCompleted);
        }
      });
      alert('Upload successful!');
    } catch (error) {
      console.error('Upload failed', error);
      alert('Failed to upload. Please try again.');
    } finally {
      setUploading(false);
    }
  };

  return (
    <Container maxWidth='md'>
      <Typography variant='h4'>Upload Sequence</Typography>
      <form onSubmit={handleSubmit}>
        <input
          type='file'
          onChange={handleFileChange}
          accept='.fastq,.bam,.vcf'
        />
        {file && (
          <>
            <Typography variant='body1'>{file.name}</Typography>
            {uploading && (
              <>
                <LinearProgress variant='determinate' value={uploadProgress} />
                <Typography>{`${uploadProgress}%`}</Typography>
              </>
            )}
            {!uploading && (
              <Button type='submit' variant='contained' color='primary'>
                Upload
              </Button>
            )}
          </>
        )}
      </form>
    </Container>
  );
};

export default SequenceUpload;
