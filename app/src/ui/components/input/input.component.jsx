import React from 'react'

export function Input({ name, label, handleChange, value, type, required,id, placeholder, classInput, classLabel}) {
  function onChange(event) {
    handleChange(event.target.value)
  }

  return (
    <>
     {label?<label className={classLabel} htmlFor={name}>{label}</label>  : ""}
      <input
        type={type}
        onChange={onChange}
        name={name}
        value={value}
        required={required}
        id={id}
        className={classInput}
        placeholder={placeholder}
        spellCheck="false"
      />
    </>
  )
}
