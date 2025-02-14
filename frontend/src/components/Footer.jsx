import { Typography, Container, Link } from '@mui/material';

const Footer = () => (
  <footer
    style={{ marginTop: 'auto', backgroundColor: '#f5f5f5', padding: '20px 0' }}
  >
    <Container maxWidth='sm'>
      <Typography variant='body2' color='textSecondary' align='center'>
        © {new Date().getFullYear()} GenoInsight. All rights reserved.
        <br />
        <Link color='inherit' href='https://genoinsight.com'>
          Visit our website
        </Link>
      </Typography>
    </Container>
  </footer>
);

export default Footer;
