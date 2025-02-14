import { Link as RouterLink } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';

const Header = () => (
  <AppBar position='static'>
    <Toolbar>
      <Typography variant='h6' component='div' sx={{ flexGrow: 1 }}>
        GenoInsight
      </Typography>
      <Box sx={{ display: 'flex', alignItems: 'center' }}>
        <Button color='inherit' component={RouterLink} to='/'>
          Home
        </Button>
        <Button color='inherit' component={RouterLink} to='/upload'>
          Upload
        </Button>
        <Button color='inherit' component={RouterLink} to='/analysis'>
          Analysis
        </Button>
        <Button color='inherit' component={RouterLink} to='/reports'>
          Reports
        </Button>
        <Button color='inherit' component={RouterLink} to='/query'>
          Query
        </Button>
      </Box>
    </Toolbar>
  </AppBar>
);

export default Header;
