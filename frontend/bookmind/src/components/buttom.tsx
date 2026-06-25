function Button(props: { count: number }) {
    return (
        <button
          type="button"
          >
          Count is {props.count}
        </button>
    )
}

export default Button;