import { TextField } from '@material-ui/core';
import Autocomplete from '@material-ui/lab/Autocomplete';
import React from 'react'


export default function ComboBox({label,array, func, id}) {


  return (
      <>
      <Autocomplete
      id={id}
      size="small"
      options={array}
      onChange={(e,valor) => func(valor)}
      getOptionLabel={(option) => option.title}
      style={{ width: 170 }}
      renderInput={(params) => <TextField {...params} label={label} variant="outlined" />}
    />
    </>
  );
}
