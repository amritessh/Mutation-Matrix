import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper
} from '@mui/material';

const VariantViewer = ({ variants }) => (
  <TableContainer component={Paper}>
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
        {variants.map((variant, index) => (
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
);

export default VariantViewer;
